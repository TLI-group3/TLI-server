from fastapi import FastAPI

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/demo")
async def root():
    return {"cars": [{"Lamborghini": {'Aventador', 'S', '2020', 100000}}, {"Ford": {'Explorer', 'SUV', '2021', 50000}}]}


@app.after_request
def after_request(response):
  response.headers.set('Access-Control-Allow-Origin', '*')
  response.headers.set('Access-Control-Allow-Headers', 'Content-Type,Authorization')
  response.headers.set('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE,OPTIONS')
  return response 
