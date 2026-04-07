from django.urls import path
from . import views

urlpatterns = [
    path('products/', views.get_products, name='product-list'),
    path('products/<int:pk>/', views.product_detail, name='product-detail'),
]