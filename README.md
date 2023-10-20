# Challenge-3
Microsserviços: 

Ms-melhoria : cadastro de propostas de melhoria,quantificação na votação, integração sincrona com o ms-user para validação de funcionários. 
Ms-user: cadastro de funcionarios, validações de erro por identificação do CPF, utilização de validação por CPF, neste caso utilize um gerador de CPF, segue abaixo exemplos: 

https://www.4devs.com.br/gerador_de_cpf 

853.956.060-76
505.039.530-59
796.401.400-58

Configuração de tempo no método PUT para cadastro de votação, (o tempo deve ser informado no corpo da requisição para que o teste seja efetuado com sucesso)
segue abaixo exemplo: 
Neste exemplo consideramos que o tempo não foi especificado então a votação durará um minuto.

{
    "idMelhoria" : 9,
    "cpf": "478.732.130-72",
    "voto" : "APROVAR",
    "min": 0      
}
    

Configuração EUREKA: 

Servidor eureka configurado na porta: 8081

API GATEWAY configurada na porta: 8082

Utilização de Load Balancer 
