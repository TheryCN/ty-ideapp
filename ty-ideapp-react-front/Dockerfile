FROM node:10.15.3

# Copy
COPY build/ .

# Make port 5000 available to the world outside this container
EXPOSE 5000

RUN npm install -g serve

# Run app
CMD ["serve","-s","build"]
