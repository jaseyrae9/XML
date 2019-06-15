# Generated by Django 2.2.2 on 2019-06-12 16:52

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('agentski_modul', '0007_auto_20190611_1114'),
    ]

    operations = [
        migrations.CreateModel(
            name='Customer',
            fields=[
                ('id', models.IntegerField(primary_key=True, serialize=False)),
                ('firstName', models.CharField(max_length=32)),
                ('lastName', models.CharField(max_length=32)),
            ],
        ),
        migrations.CreateModel(
            name='Message',
            fields=[
                ('id', models.IntegerField(primary_key=True, serialize=False)),
                ('date', models.DateTimeField()),
                ('text', models.CharField(max_length=512)),
            ],
        ),
        migrations.AlterField(
            model_name='resrvation',
            name='customer_id',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='agentski_modul.Customer'),
        ),
    ]
