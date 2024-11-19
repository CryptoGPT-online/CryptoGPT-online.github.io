class Config:
    def __init__(self, temperature=0, model="gpt-3.5-turbo", query_times=5, prompt="prompt_insecure"):
        self.temperature = temperature
        self.model = model
        self.query_times = query_times
        self.prompt = prompt
