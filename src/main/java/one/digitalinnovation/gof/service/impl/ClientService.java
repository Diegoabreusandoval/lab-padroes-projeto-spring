package one.digitalinnovation.gof.service.impl;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 @@ -16,40 +22,62 @@
 @Service
 public class ClienteServiceImpl implements ClienteService {

 // TODO Singleton: Injetar os componentes do Spring com @Autowired.
 // TODO Strategy: Implementar os métodos definidos na interface.
 // TODO Facade: Abstrair integrações com subsistemas, provendo uma interface simples.
 // Singleton: Injetar os componentes do Spring com @Autowired.
 @Autowired
 private ClienteRepository clienteRepository;
 @Autowired
 private EnderecoRepository enderecoRepository;
 @Autowired
 private ViaCepService viaCepService;

 // Strategy: Implementar os métodos definidos na interface.
 // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

 @Override
 public Iterable<Cliente> buscarTodos() {
 // FIXME Buscar todos os Clientes.
 return null;
 // Buscar todos os Clientes.
 return clienteRepository.findAll();
 }

 @Override
 public Cliente buscarPorId(Long id) {
 // FIXME Buscar Cliente por ID.
 return null;
 // Buscar Cliente por ID.
 Optional<Cliente> cliente = clienteRepository.findById(id);
 return cliente.get();
 }

 @Override
 public void inserir(Cliente cliente) {
 // FIXME Verificar se o Endereco do Cliente já existe (pelo CEP).
 // FIXME Caso não exista, integrar com o ViaCEP e persistir o retorno.
 // FIXME Inserir Cliente, vinculando o Endereco (novo ou existente).
 salvarClienteComCep(cliente);
 }

 @Override
 public void atualizar(Long id, Cliente cliente) {
 // FIXME Buscar Cliente por ID, caso exista:
 // FIXME Verificar se o Endereco do Cliente já existe (pelo CEP).
 // FIXME Caso não exista, integrar com o ViaCEP e persistir o retorno.
 // FIXME Alterar Cliente, vinculando o Endereco (novo ou existente).
 // Buscar Cliente por ID, caso exista:
 Optional<Cliente> clienteBd = clienteRepository.findById(id);
 if (clienteBd.isPresent()) {
 salvarClienteComCep(cliente);
 }
 }

 @Override
 public void deletar(Long id) {
 // FIXME Deletar Cliente por ID.
 // Deletar Cliente por ID.
 clienteRepository.deleteById(id);
 }

 private void salvarClienteComCep(Cliente cliente) {
 // Verificar se o Endereco do Cliente já existe (pelo CEP).
 String cep = cliente.getEndereco().getCep();
 Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
 // Caso não exista, integrar com o ViaCEP e persistir o retorno.
 Endereco novoEndereco = viaCepService.consultarCep(cep);
 enderecoRepository.save(novoEndereco);
 return novoEndereco;
 });
 cliente.setEndereco(endereco);
 // Inserir Cliente, vinculando o Endereco (novo ou existente).
 clienteRepository.save(cliente);

 }
