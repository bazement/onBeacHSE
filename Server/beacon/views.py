from django.http import HttpResponse, JsonResponse
from django.shortcuts import get_object_or_404, render
from .models import Information


def index(request):
    try:
        major = request.GET['major']
        minor = request.GET['minor']
        information = get_object_or_404(Information, major_number=major, minor_number=minor)
        response = dict()
        response['info'] = information.info
    except KeyError:
        return HttpResponse("Didn't got it")
    else:
        return JsonResponse(response)
