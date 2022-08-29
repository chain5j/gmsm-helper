package org.zz.gmhelper.test;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.zz.gmhelper.BCECUtil;
import org.zz.gmhelper.SM2Util;
import org.zz.gmhelper.Util;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2022/8/22 11:32
 * @Copyright Copyright@2022
 */
public class Sm2Test {

    @Test
    public void testSign() throws Exception {
        try {
            System.out.println("===========================");
            // TUSI5584GF1YOL6XLPRYDQ5X5FH7CE1MJF75P
            String prvKeyHex = "0x9f4007d9cd72e0d2b05084781a368b866e3149bb4421e97bb2f623580bff6a02";
            BCECPrivateKey priKey = BCECUtil.convertHexToBCECPrivateKey(prvKeyHex);

            byte[] sign = SM2Util.sign(priKey, "1234567812345678".getBytes(), "你好，123！@".getBytes());
            System.out.println(Hex.toHexString(sign));
            // sign format =
            // 30 + len(z) + 02 + len(r) + r + 02 + len(s) + s, z being what follows its size,
            // ie 02+len(r)+r+02+len(s)+s
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSign2() throws Exception {
        try {
            System.out.println("===========================");
            // TUSI57EWZAGNM67Y1X494LZY707KWA35LS5II
            String prvKeyHex = "0x9283eaa857d704007b5cb0a71d6766ddf67a18079429733d823646ee0e016797";
            BCECPrivateKey priKey = BCECUtil.convertHexToBCECPrivateKey(prvKeyHex);

            BigInteger privateKeyBigInteger = Util.toBigInt(prvKeyHex);
            ECDomainParameters domainParams = SM2Util.DOMAIN_PARAMS;
            ECPoint point = publicPointFromPrivate(privateKeyBigInteger);
            ECPublicKeyParameters pubKey = new ECPublicKeyParameters(point, domainParams);
            ECParameterSpec spec = new ECParameterSpec(domainParams.getCurve(), domainParams.getG(),
                    domainParams.getN(),
                    domainParams.getH());
            BCECPublicKey publicKey = new BCECPublicKey("EC", pubKey, spec, BouncyCastleProvider.CONFIGURATION);

            byte[] sign = SM2Util.sign(priKey, "1234567812345678".getBytes(), "你好，123！@".getBytes());
            System.out.println("sign=" + Hex.toHexString(sign));
            boolean verify = SM2Util.verify(publicKey, "你好，123！@".getBytes(), sign);
            System.out.println("verify=" + verify);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ECPoint publicPointFromPrivate(BigInteger privKey) {
        ECPrivateKeyParameters keyParameters = BCECUtil.createECPrivateKeyParameters(privKey, SM2Util.DOMAIN_PARAMS);
        ECDomainParameters domainParameters = keyParameters.getParameters();
        return (new FixedPointCombMultiplier()).multiply(domainParameters.getG(), keyParameters.getD());
    }
}
