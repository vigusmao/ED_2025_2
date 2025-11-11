import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {

    private Trie minhaTrie;

    @Before
    public void setUp() {
        minhaTrie = new Trie();
        minhaTrie.adicionaPalavra("goiaba");
    }

    @Test
    public void testePalavraExistente() {
        assertTrue("Deveria ter encontrado a palavra que foi adicionada",
                minhaTrie.temPalavra("goiaba"));
    }

    @Test
    public void testePalavraNaoExistente() {
        assertFalse("Nao deveria ter uma palavra que nunca foi adicionada!",
                minhaTrie.temPalavra("abacate"));
    }

    @Test
    public void testePalavraNaoExistenteQueEhPrefixo() {
        assertFalse("Nao deveria ter uma palavra que nunca foi adicionada!",
                minhaTrie.temPalavra("goi"));
    }

    @Test
    public void testePrefixo() {
        assertTrue("Deve localizar prefixos de uma palavra que foi adicionada",
                minhaTrie.temPrefixo("goia"));
    }

    @Test
    public void testeContadorDePalavrasComPrefixo() {
        minhaTrie.adicionaPalavra("abacateiro");
        minhaTrie.adicionaPalavra("arara");
        minhaTrie.adicionaPalavra("aba");
        minhaTrie.adicionaPalavra("azul");
        minhaTrie.adicionaPalavra("abrir");

        assertEquals(5, minhaTrie.getContadorDePalavrasComPrefixo("a"));
        assertEquals(3, minhaTrie.getContadorDePalavrasComPrefixo("ab"));
        assertEquals(0, minhaTrie.getContadorDePalavrasComPrefixo("cvbc"));
        assertEquals(1, minhaTrie.getContadorDePalavrasComPrefixo("arara"));
    }
}
