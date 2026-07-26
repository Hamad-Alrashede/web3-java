# Web3 Java

Java examples for interacting with the Ethereum blockchain using the [Web3j](https://web3j.io/) library, designed for Ganache local development.

[![Java](https://img.shields.io/badge/Java-8%2B-orange?logo=openjdk&logoColor=white)](https://www.java.com)
[![Web3j](https://img.shields.io/badge/Web3j-4.9.0-blue)](https://web3j.io)
[![Ethereum](https://img.shields.io/badge/Ethereum-Ganache--compatible-purple?logo=ethereum&logoColor=white)](https://ethereum.org)

## Overview

This repository contains Java code examples for blockchain-based security systems, covering wallet generation, key management, and smart contract interaction.

## Files

| File | Description |
|------|-------------|
| `PublicPrivateKeys.java` | Generate a wallet file, load credentials, and display public/private keys |
| `BlockChainBasedSecurity.java` | Interact with smart contracts: register controllers and send transactions on the blockchain |

## Requirements

- Java 8+
- Web3j library
- Ganache running locally

## Setup

Add Web3j dependency to your project:

**Maven:**
```xml
<dependency>
    <groupId>org.web3j</groupId>
    <artifactId>core</artifactId>
    <version>4.9.0</version>
</dependency>
```

**Gradle:**
```gradle
implementation 'org.web3j:core:4.9.0'
```

## Usage

1. Start Ganache on `localhost:8545`
2. Compile:
   ```bash
   javac -cp web3j-core.jar:. PublicPrivateKeys.java
   ```
3. Run:
   ```bash
   java -cp web3j-core.jar:. PublicPrivateKeys
   ```

## Related Repos

- [solidity-contracts](https://github.com/Hamad-Alrashede/solidity-contracts) — Smart contracts used with these Java clients
- [web3-python](https://github.com/Hamad-Alrashede/web3-python) — Python equivalents using Web3.py

## License

MIT
