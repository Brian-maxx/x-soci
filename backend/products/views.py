from django.shortcuts import render
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import Product
from .serializers import ProductSerializer
from rest_framework import status

@api_view(['GET', 'POST'])
def get_products(request):
    if request.method == 'GET':
        products = Product.objects.all()
        serializer = ProductSerializer(products, many=True)

        return Response(serializer.data)

    elif request.method == 'POST':
        serializer = ProductSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()

        return Response(serializer.data, status=status.HTTP_201_CREATED)

@api_view(['GET', 'PUT', 'DELETE'])
def product_detail(request, pk):
    try:
        product = Product.objects.get(pk=pk)
    except Product.DoesNotExist:
        return Response({'error': 'Sản phẩm không tồn tại'}, status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = ProductSerializer(product)

        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = ProductSerializer(product, data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()

        return Response(serializer.data)

    elif request.method == 'DELETE':
        product.delete()

        return Response({'message': 'Đã xóa thành công'}, status=status.HTTP_204_NO_CONTENT)