from django.shortcuts import render
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt

# Create your views here.

@csrf_exempt
def print_hello(request):
    a = '한글01'
    return HttpResponse((a+'/')*10)
