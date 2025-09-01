from fastapi import FastAPI, File, UploadFile
from PIL import Image
import io
from transformers import pipeline

app = FastAPI()

# Modelo de clasificación de alimentos (Food101 dataset)
classifier = pipeline("image-classification", model="nateraw/food")

@app.post("/predict")
async def predict(file: UploadFile = File(...)):
    image = Image.open(io.BytesIO(await file.read()))
    preds = classifier(image)
    best_pred = preds[0]["label"]
    return {"alimento": best_pred}
