from django.db import models


class Information(models.Model):
    major_number = models.IntegerField()
    minor_number = models.IntegerField()
    info = models.CharField(max_length=300)

    # def __str__(self):
    #     return str(self.info)
