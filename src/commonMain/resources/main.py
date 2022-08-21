import string
import time
from dataclasses import dataclass
import requests
from datetime import datetime
from pms7003 import Pms7003Sensor

now = datetime.now()


@dataclass
class ParticlesItem:
    particles: int
    lastUpdated: int
    id: int = -1377309362
    path: string = "http://127.0.0.1:9090/particles/patch/%d" % (id)

def patch():
    url = ParticlesItem.path
    data = {
        "lastUpdated": ParticlesItem.lastUpdated,
        "particles": ParticlesItem.particles,
        "location": "",
        "id": ParticlesItem.id,
    }
    headers = {
        "Content-type": "application/json"
    }
    response = requests.post(url, headers=headers, json=data)
    print("Response: %s" % (response.status_code))

#def calcParticles():




#sensor = Pms7003Sensor("dev/serial0")
while True:
    #sensor.wakeup()
    #time.sleep(30)
    ParticlesItem.particles = int(input("Enter particles: ")) #sensor.read()
    ParticlesItem.lastUpdated = int(now.strftime("%d%m%H%M"))
    patch()
    #sensor.sleep()
    time.sleep(60)