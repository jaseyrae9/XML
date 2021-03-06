# Generated by Django 2.2.2 on 2019-06-29 20:48

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('agentski_modul', '0031_auto_20190629_1616'),
    ]

    operations = [
        migrations.AddField(
            model_name='recension',
            name='roomId',
            field=models.ForeignKey(default=103, on_delete=django.db.models.deletion.CASCADE, to='agentski_modul.Room'),
            preserve_default=False,
        ),
        migrations.AlterField(
            model_name='roomfotos',
            name='room',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='fotos', to='agentski_modul.Room'),
        ),
    ]
