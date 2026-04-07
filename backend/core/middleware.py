import time
from urllib import request
import logging

logger = logging.getLogger('api_logger')

class APILoggerMiddleware:
    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        start_time = time.time()
        response = self.get_response(request)
        duration = time.time() - start_time
        log_message = f"Method: {request.method} | Đường dẫn: {request.path} | Thời gian: {duration:.2f} giây"
        logger.info(log_message)
        return response