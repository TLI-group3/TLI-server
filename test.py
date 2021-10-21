from fastapi import FastAPI

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/demo")
async def root():
    return {"cars": [{"Lamborghini": {'Aventador', 'S', '2020', 100000}}, {"Ford": {'Explorer', 'SUV', '2021', 50000}}]}