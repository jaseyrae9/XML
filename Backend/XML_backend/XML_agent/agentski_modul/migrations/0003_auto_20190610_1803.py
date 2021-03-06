# Generated by Django 2.2.2 on 2019-06-10 18:03

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('agentski_modul', '0002_auto_20190503_1223'),
    ]

    operations = [
        migrations.AddField(
            model_name='hotel',
            name='name',
            field=models.CharField(default='', max_length=30),
        ),
        migrations.AlterField(
            model_name='additionalservice',
            name='name',
            field=models.CharField(max_length=256),
        ),
        migrations.AlterField(
            model_name='roomcategory',
            name='description',
            field=models.CharField(max_length=256),
        ),
        migrations.AlterField(
            model_name='roomcategory',
            name='number_of_stars',
            field=models.IntegerField(),
        ),
        migrations.AlterField(
            model_name='roomtype',
            name='name',
            field=models.CharField(max_length=256),
        ),
        migrations.DeleteModel(
            name='DayResrvation',
        ),
    ]
