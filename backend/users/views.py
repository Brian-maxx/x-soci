from django.shortcuts import render
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import User
from .serializers import UserSerializer
from rest_framework import status
from django.contrib.auth.hashers import check_password

@api_view(['GET', 'POST'])
def users(request):
    if request.method == 'GET':
        users = User.objects.all()
        serializer = UserSerializer(users, many=True)

        return Response(serializer.data)

    elif request.method == 'POST':
        serializer = UserSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=status.HTTP_201_CREATED)
    
@api_view(['POST'])
def user_login(request):
    print(request.data)
    email = request.data.get('email')
    password = request.data.get('password')
    if not email or not password:
        return Response({'error': 'Email và mật khẩu là bắt buộc'}, status=status.HTTP_400_BAD_REQUEST)
    try:
        user = User.objects.get(email=email)
        if check_password(password, user.password):
            serializer = UserSerializer(user)
            return Response(serializer.data)
        else:
            return Response({'error': 'Email hoặc mật khẩu không đúng'}, status=status.HTTP_401_UNAUTHORIZED)
    except User.DoesNotExist:
        return Response({'error': 'Email hoặc mật khẩu không đúng'}, status=status.HTTP_401_UNAUTHORIZED)


@api_view(['GET', 'PUT', 'DELETE'])
def user_detail(request, pk):
    try:
        user = User.objects.get(pk=pk)
    except User.DoesNotExist:
        return Response({'error': 'Người dùng không tồn tại'}, status=status.HTTP_404_NOT_FOUND)
    
    if request.method == 'GET':
        serializer = UserSerializer(user)

        return Response(serializer.data)
    
    elif request.method == 'PUT':
        serializer = UserSerializer(user, data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
    
        return Response(serializer.data)
    
    elif request.method == 'DELETE':
        user.delete()

        return Response({'message': 'Đã xóa thành công'}, status=status.HTTP_204_NO_CONTENT)