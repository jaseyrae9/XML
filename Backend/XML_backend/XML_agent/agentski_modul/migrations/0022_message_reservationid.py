# Generated by Django 2.2.2 on 2019-06-22 18:57

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('agentski_modul', '0021_auto_20190622_1853'),
    ]

    operations = [
        migrations.AddField(
            model_name='message',
            name='reservationId',
            field=models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to='agentski_modul.Resrvation'),
            preserve_default=False,
        ),
    ]
