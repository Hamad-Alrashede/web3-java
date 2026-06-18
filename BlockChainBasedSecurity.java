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
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class BlockChainBasedSecurity {
    private static final String GANACHE_URL = "http://localhost:8545";
    private static final String CONTRACT_ADDRESS = "0xYourContractAddressHere";
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(2000000);
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(50000000000L);

    private Web3j web3j;
    private TransactionManager transactionManager;

    public BlockChainBasedSecurity(String privateKey) {
        this.web3j = Web3j.build(new HttpService(GANACHE_URL));
        Credentials credentials = Credentials.create(privateKey);
        this.transactionManager = new ClientTransactionManager(web3j, credentials.getAddress());
    }

    public String registerController(String controllerAddress, String adminPrivateKey) throws Exception {
        Credentials credentials = Credentials.create(adminPrivateKey);
        EthGetTransactionCount ethGetTransactionCount = web3j
                .ethGetTransactionCount(credentials.getAddress(), org.web3j.protocol.core.DefaultBlockParameterName.PENDING)
                .sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        String functionSignature = "registerController(address)";
        String data = "0x" + functionSignature + String.format("%64s", controllerAddress.substring(2)).replace(' ', '0');

        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce, GAS_PRICE, GAS_LIMIT, CONTRACT_ADDRESS, data);

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
        return ethSendTransaction.getTransactionHash();
    }

    public String sendTransaction(String toAddress, BigInteger value, String privateKey) throws Exception {
        Credentials credentials = Credentials.create(privateKey);
        EthGetTransactionCount ethGetTransactionCount = web3j
                .ethGetTransactionCount(credentials.getAddress(), org.web3j.protocol.core.DefaultBlockParameterName.PENDING)
                .sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                nonce, GAS_PRICE, GAS_LIMIT, toAddress, value);

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
        return ethSendTransaction.getTransactionHash();
    }

    public static void main(String[] args) {
        System.out.println("BlockChainBasedSecurity - Java Web3j Example");
        System.out.println("Connect to Ganache at: " + GANACHE_URL);
    }
}
