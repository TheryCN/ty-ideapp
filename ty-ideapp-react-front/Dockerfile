FROM node:10.15.3

# Copy
COPY . .

# Build & deploy
RUN npm install
RUN npm build
RUN npm install -g serve

# Make port 5000 available to the world outside this container
EXPOSE 5000

# Run app
CMD ["serve","-s","build"]
