from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware


app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/demo")
async def root():
    return {"cars":[{price: 100000, make: "Aventador", model: "S",year: "2020"},{price: 50000,make: "SUV",year: "2021",model:"Explorer"}]}

origins = [
    "http://localhost.tiangolo.com",
    "https://localhost.tiangolo.com",
    "http://localhost",
    "http://localhost:8080",
    "http://localhost:3000",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
