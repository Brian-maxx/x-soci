from django.http import JsonResponse
import logging

logger = logging.getLogger('api_logger')

class ProductValidationMiddleware:
    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        if request.path.startswith('/api/products/') and request.method in ['POST', 'PUT', 'DELETE']:
            client_role = request.META.get('HTTP_X_CLIENT_ROLE')
            if client_role != 'admin':
                log_message = f"Unauthorized access attempt: Method: {request.method} | Path: {request.path} | Client Role: {client_role}"
                logger.warning(log_message)
                return JsonResponse({'error': 'Bạn không có quyền thực hiện hành động này'}, status=403)
        
        response = self.get_response(request)

        return response