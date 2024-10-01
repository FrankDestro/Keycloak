# ![teste](https://www.xpand-it.com/wp-content/uploads/2020/06/Keycloak-logo.png)  
Documentação do curso de Keycloak 

## Estrutura KeyCloak. 

* Keycloak: A plataforma de gerenciamento de identidade e acesso.

* Realms: Contêineres para isolar conjuntos de configurações e dados. Cada realm tem sua própria configuração de autenticação, usuários e clients.

* Clients: Aplicativos ou serviços que interagem com o Keycloak para autenticação e autorização. Cada client está associado a um realm específico.

* Users: Os indivíduos que utilizam o client, autenticando-se no Keycloak.

* Groups: Conjuntos de usuários que podem ser gerenciados em conjunto, facilitando a atribuição de roles.

* Roles: Permissões que podem ser atribuídas a usuários ou grupos, definindo o que eles podem fazer dentro do client.

## Roles e os tipos :


### Realm Roles

Os Realm Roles são papéis globais dentro de um Realm e são atribuídos a usuários em nível de Realm. Eles não estão vinculados a um cliente específico, mas podem ser usados em qualquer cliente dentro daquele Realm.

Características:
* Escopo Global: São definidos no nível do Realm e podem ser atribuídos a qualquer usuário ou grupo dentro do Realm.
* Uso Geral: Geralmente usados para definir permissões que precisam ser aplicadas a vários clientes. Por exemplo, você pode ter um papel "admin" que concede permissões administrativas em diversos aplicativos dentro do Realm.
* Atribuição: Você pode atribuir um Realm Role diretamente a um usuário ou a um grupo, e todos os membros do grupo herdarão esse papel.

#### Exemplo:
Se você criar um Realm Role chamado admin, ele poderá ser atribuído a qualquer usuário dentro do Realm, e esse papel poderá ser reconhecido em qualquer cliente dentro daquele Realm.

### Client Roles

Os Client Roles são papéis específicos de um cliente (aplicação) no Keycloak. Eles controlam as permissões dentro do contexto de um cliente específico.

Características:
* Escopo Específico de Cliente: Estão vinculados a um cliente específico e só têm significado dentro do contexto desse cliente.
* Segurança em Nível de Aplicação: Usados para definir permissões específicas para recursos dentro de um cliente. Por exemplo, se um cliente for uma API, você pode definir roles como user ou manager, que concedem permissões diferentes dentro dessa aplicação.
* Atribuição: Assim como os Realm Roles, você pode atribuir um Client Role a um usuário ou a um grupo, mas ele só se aplica ao cliente específico onde foi criado.

#### Exemplo:
Se você tiver um cliente chamado my-app, poderá criar um Client Role chamado user. Esse papel user só será relevante dentro do contexto do cliente my-app. Outro cliente não verá ou usará esse papel, a menos que explicitamente compartilhado.

### Group Roles (ou roles associadas a grupos)

Roles que são atribuídas a grupos de usuários. Quando uma role é associada a um grupo, todos os membros desse grupo herdam a role. Isso facilita a gestão de permissões para conjuntos de usuários.
Herança de Permissões:

* Quando uma role é atribuída a um grupo, todos os membros desse grupo automaticamente herdam essa role. Isso simplifica o gerenciamento de permissões, pois você não precisa atribuir roles a usuários individuais.
Facilidade de Gestão:

* Gerenciar permissões para um grupo é mais eficiente do que fazer isso para cada usuário individualmente, especialmente em organizações grandes.
Combinação com Realm e Client Roles:

* Os Group Roles podem ser combinados com Realm Roles e Client Roles, permitindo um controle de acesso mais granular e flexível. Você pode, por exemplo, dar a um grupo uma role de acesso a um recurso específico de um client.
Aplicação em Cenários de Colaboração:

Ideal para situações em que os usuários precisam ter permissões similares, como em equipes ou departamentos.
