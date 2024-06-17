> Gerar uma chave privada RSA 
    openssl genrsa > app.key

> Gerar uma chave publica baseada na privada    
    openssl rsa -in app.key -pubout -out app.pub