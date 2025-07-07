FROM node:20 AS build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci
COPY . .
RUN npm run build
RUN find dist -type f | grep -i "index.html" || echo "No se encontró index.html"
RUN ls -la dist || echo "No hay carpeta dist"

# Crear carpeta de salida y copiar archivos
RUN mkdir -p /app/output && \
    if [ -d "dist/browser" ]; then \
      echo "Copiando desde dist/browser" && \
      cp -r dist/browser/* /app/output/; \
    elif [ -f "dist/browser/index.html" ]; then \
      echo "Encontrado index.html en browser" && \
      cp -r dist/browser/* /app/output/; \
    elif [ -d "dist" ] && [ -f "dist/index.html" ]; then \
      echo "Copiando desde dist raíz" && \
      cp -r dist/* /app/output/; \
    else \
      echo "Estructura detectada:" && find dist -type d && \
      find dist -name "*.html" && \
      # Buscar recursivamente cualquier archivo index.html
      INDEXPATH=$(find dist -name "index.html" | head -1) && \
      if [ -n "$INDEXPATH" ]; then \
        PARENTDIR=$(dirname "$INDEXPATH") && \
        echo "Copiando desde $PARENTDIR" && \
        cp -r "$PARENTDIR"/* /app/output/; \
      else \
        echo "No se pudo encontrar index.html" && exit 1; \
      fi \
    fi

FROM nginx:alpine
RUN rm -rf /usr/share/nginx/html/* && rm -f /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/output/ /usr/share/nginx/html/
RUN echo "Contenido final:" && ls -la /usr/share/nginx/html/
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
