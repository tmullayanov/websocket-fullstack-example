FROM node:18-alpine as prepare

ENV CI=true
ENV PORT=3000

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY ./package*.json yarn.* ./
# RUN yarn config set strict-ssl false # hello corporate network

FROM prepare as development
RUN yarn install --silent
COPY . ./
CMD ["yarn", "start"]

FROM prepare as builder

COPY . ./
RUN yarn install
RUN yarn build

FROM nginx:stable-alpine as production
RUN mkdir -p /var/www/frontend
COPY ./nginx/nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/build /var/www/frontend