from django.db import models

class Product(models.Model):
    name = models.CharField(max_length=255, null=False, verbose_name='Tên sản phẩm')
    price = models.DecimalField(max_digits=10, decimal_places=2, null=False, verbose_name='Giá sản phẩm')
    description = models.TextField(blank=True, verbose_name='Mô tả sản phẩm')
    created_at = models.DateTimeField(auto_now_add=True, verbose_name='Ngày tạo')
    updated_at = models.DateTimeField(auto_now=True, verbose_name='Ngày cập nhật')

    def __str__(self):
        return self.name