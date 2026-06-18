import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;
import java.math.BigInteger;


public class BlockChainBasedSecurity {


    private static final String CONTRACT_ADDRESS = "0xC03226fEBD45ca82BAfEC6Ca72F863f5158f395a";
    private static final String PRIVATE_KEY = "private_key_here"; 
    private static final Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));


    public static void main(String[] args) throws Exception {
        // Check if connected
        if (!web3.web3ClientVersion().send().hasError()) {
            System.out.println("Connected to Ganache node");
        } else {
            throw new RuntimeException("Failed to connect to Ganache node");
        }


        // Load the contract
        Credentials credentials = Credentials.create(PRIVATE_KEY);
        TransactionManager transactionManager = new ClientTransactionManager(web3, CONTRACT_ADDRESS);
        ContractGasProvider gasProvider = new DefaultGasProvider();
        SmartContract contract = SmartContract.load(CONTRACT_ADDRESS, web3, transactionManager, gasProvider);


        // Register a new controller
        String controllerAddress = "0xControllerAddress"; 
        String txHash = registerController(contract, controllerAddress, credentials);
        System.out.println("Register Controller Transaction Hash: " + txHash);


        // Remove a specific controller
        txHash = removeController(contract, controllerAddress, credentials);
        System.out.println("Remove Controller Transaction Hash: " + txHash);


        // Send a message
        String message = "Update Data-Flow Entry";
        txHash = sendMessage(contract, message, credentials);
        System.out.println("Send Message Transaction Hash: " + txHash);


        // Blacklist a controller
        txHash = blacklistController(contract, controllerAddress, credentials);
        System.out.println("Blacklist Controller Transaction Hash: " + txHash);
    }


    private static String registerController(SmartContract contract, String controllerAddress, Credentials credentials) throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();


        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                DefaultGasProvider.GAS_PRICE,
                DefaultGasProvider.GAS_LIMIT,
                CONTRACT_ADDRESS,
                contract.registerController(controllerAddress).encodeFunctionCall()
        );


        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();


        return ethSendTransaction.getTransactionHash();
    }


    private static String removeController(SmartContract contract, String controllerAddress, Credentials credentials) throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();


        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                DefaultGasProvider.GAS_PRICE,
                DefaultGasProvider.GAS_LIMIT,
                CONTRACT_ADDRESS,
                contract.removeSpecificController(controllerAddress).encodeFunctionCall()
        );


        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();


        return ethSendTransaction.getTransactionHash();
    }


    private static String sendMessage(SmartContract contract, String message, Credentials credentials) throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();


        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                DefaultGasProvider.GAS_PRICE,
                DefaultGasProvider.GAS_LIMIT,
                CONTRACT_ADDRESS,
                contract.sendMessage(message).encodeFunctionCall()
        );


        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();


        return ethSendTransaction.getTransactionHash();
    }


    private static String blacklistController(SmartContract contract, String controllerAddress, Credentials credentials) throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();


        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                DefaultGasProvider.GAS_PRICE,
                DefaultGasProvider.GAS_LIMIT,
                CONTRACT_ADDRESS,
                contract.blacklistController(controllerAddress).encodeFunctionCall()
        );


        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();


        return ethSendTransaction.getTransactionHash();
    }
}
