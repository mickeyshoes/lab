from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
import json

# Create your views here.

@csrf_exempt
def print_hello(request):
    a = '한글01'
    return HttpResponse((a+'/')*10)

@csrf_exempt
def accept_hello(request):
    if(request.method=='POST'):
        received_json_data = json.loads(request.body)
        """print(received_json_data)
        print(type(received_json_data))"""
        for key in received_json_data:
            print(key)
            received_json_data[key]= received_json_data[key]+5
        print(received_json_data)
        return HttpResponse(received_json_data)
    else:
        return HttpResponse("json type data received fail") 
       
