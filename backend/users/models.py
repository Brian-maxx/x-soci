from django.db import models
from django.contrib.auth.hashers import make_password
import uuid

class User(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    email = models.EmailField(unique=True, null=False)
    username = models.CharField(max_length=255, unique=True, null=False)
    password = models.CharField(max_length=255, null=False)
    phone_number = models.CharField(max_length=20, null=True, blank=True)
    address = models.CharField(max_length=255, null=True, blank=True)
    is_active = models.BooleanField(default=True)
    is_deleted = models.BooleanField(default=False)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def setPassword(self, raw_password):
        self.password = make_password(raw_password)