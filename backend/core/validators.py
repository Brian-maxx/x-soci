from rest_framework import serializers
import logging

logger = logging.getLogger('api_logger')

def validate_not_empty(value, field_name):
    if value is None or value == '':
        logger.warning(f"Validation error: {field_name} không được để trống | Value: '{value}'")
        raise serializers.ValidationError(f"{field_name} không được để trống")
    return value

def validate_unique(model, field_name, value):
    if model.objects.filter(**{field_name: value}).exists():
        logger.warning(f"Validation error: {field_name} đã tồn tại | Value: '{value}'")
        raise serializers.ValidationError(f"{field_name} đã tồn tại")
    return value