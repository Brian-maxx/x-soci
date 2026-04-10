from rest_framework import serializers
from core.validators import validate_not_empty, validate_unique
from .models import User
import logging

logger = logging.getLogger('api_logger')

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'
        extra_kwargs = {
            'password': {'write_only': True}
        }
        
    def validate_email(self, value):
        validate_not_empty(value, "Email")
        validate_unique(User, 'email', value)

        return value
    
    def validate_username(self, value):
        validate_not_empty(value, "Username")
        validate_unique(User, 'username', value)

        return value
    
    def create(self, validated_data):
        password = validated_data.get('password', None)
        validate_not_empty(password, "Password")        
        user = User(**validated_data)
        user.setPassword(password)
        user.save()

        return user