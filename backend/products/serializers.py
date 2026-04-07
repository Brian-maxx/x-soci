from rest_framework import serializers
from .models import Product
import logging

logger = logging.getLogger('api_logger')

class ProductSerializer(serializers.ModelSerializer):
    class Meta:
        model = Product
        fields = '__all__'

    def validate_name(self, value):
        if value == '' or value is None:
            log_message = f"Validation error: Tên sản phẩm không được để trống | Value: '{value}'"
            logger.warning(log_message)
            raise serializers.ValidationError('Tên sản phẩm không được để trống')
        banned_words = ['hàng fake', 'lừa đảo', 'kém chất lượng']
        for word in banned_words:
            if word in value.lower():
                log_message = f"Validation error: Tên sản phẩm chứa từ cấm | Value: '{value}' | Banned Word: '{word}'"
                logger.warning(log_message)
                raise serializers.ValidationError(f'Tên sản phẩm không được chứa từ "{word}"')

        return value
    
    def validate_price(self, value):
        if value < 0:
            log_message = f"Validation error: Giá sản phẩm phải là số dương | Value: {value}"
            logger.warning(log_message)
            raise serializers.ValidationError('Giá sản phẩm phải là số dương')
        if value < 10000:
            log_message = f"Validation error: Giá sản phẩm phải lớn hơn hoặc bằng 10,000 VND | Value: {value}"
            logger.warning(log_message)
            raise serializers.ValidationError('Giá sản phẩm phải lớn hơn hoặc bằng 10,000 VND')

        return value
    
    def validate(self, data):
        name = data.get('name', '')
        description = data.get('description', '')
        if name and description and name.lower() == description.lower():
            log_message = f"Validation error: Mô tả sản phẩm không được giống y hệt tên sản phẩm | Name: '{name}' | Description: '{description}'"
            logger.warning(log_message)
            raise serializers.ValidationError({
                "description": "Mô tả sản phẩm không được giống y hệt tên sản phẩm, hãy viết chi tiết hơn!"
            })