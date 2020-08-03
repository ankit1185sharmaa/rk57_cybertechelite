from django.shortcuts import render
from django.core.mail import send_mail, send_mass_mail
from django.views.decorators.csrf import csrf_protect
from django.http import HttpResponse
# Create your views here.

def Home(request):
    return render(request, 'try.html')

def Recieve(request):
    if request.method == "POST":
        if 'speechToText' in request.POST:
            message1 = ('DANGER!!!', request.POST["speechToText"], '', [])
            send_mass_mail((message1,), fail_silently=False)
            return HttpResponse('Your message is conveyed to your people')
            
