from mongo import MongoClient
import os
from dotenv import load_dotenv

load_dotenv()

db_uri = os.getenv("DB_URI")
db_name = os.getenv("DB_NAME")

try:
    client = MongoClient(db_uri)
    db = client[db_name]

except Exception as e:
    print(f"Error connecting to MongoDB: {e}")