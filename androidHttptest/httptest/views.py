from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt

# Create your views here.

@csrf_exempt
def print_hello(request):
    a = '한글01'
    return HttpResponse((a+'/')*10)

@csrf_exempt
def accept_hello(request):
    print_change = request.POST.get('print','')
    print_json = print_change + "ack"
    return HttpResponse(print_json)
