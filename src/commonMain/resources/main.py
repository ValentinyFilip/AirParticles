import string
import time
from dataclasses import dataclass
import requests
from datetime import datetime
from pms7003 import Pms7003Sensor

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
    try:
        response = requests.post(url, headers=headers, json=data)
    except:
        print("error - couldnt finalize post request")
    else:
        print("Response: %s" % (response.status_code))

#sensor = Pms7003Sensor("dev/serial0")
while True:
    now = datetime.now()
    #sensor.wakeup()
    #time.sleep(30)

    #placeHolderParticles = sensor.read()
    #ParticlesItem.particles = placeHolderParticles["pm2_5"]
    
    placeHolderParticles = int(input("Enter particles: "))
    ParticlesItem.particles = placeHolderParticles
    
    ParticlesItem.lastUpdated = int(now.strftime("%d%m%H%M"))
    patch()
    #sensor.sleep()
    time.sleep(60)