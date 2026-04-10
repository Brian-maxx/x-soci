from django.urls import path
from . import views

urlpatterns = [
    path('user/', views.users, name='user-list'),
    path('user/login/', views.user_login, name='user-login'),
    path('user/<int:pk>/', views.user_detail, name='user-detail'),
]