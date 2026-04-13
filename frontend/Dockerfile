FROM node:20-alpine

RUN apk update && apk add --no-cache libc6-compat python3 make g++

WORKDIR /app

COPY package.json package-lock.json* ./
RUN npm install
RUN npm install --include=dev

COPY . .

EXPOSE 5173

CMD ["npm", "run", "dev", "--", "--host"]