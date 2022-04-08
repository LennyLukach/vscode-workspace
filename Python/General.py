from grpc import Status
import requests

response = requests.get("https://buckley.myschoolapp.com/app/student#studentmyday/progress")

print(response.status_code)