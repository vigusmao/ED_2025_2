import java.util.HashMap;
import java.util.Map;

public class Trie {

    private TrieNode raiz;

    public Trie() {
        raiz = new TrieNode(null, null);
    }

    public int getTamanho() {
        return raiz.descendentes;
    }

    public void adicionaPalavra(String palavra) {
        TrieNode noCorrente = raiz;
        char[] arrayDeCaracteres = palavra.toCharArray();
        int n = palavra.length();
        int i = 0;
        while (i < n) {
            char c = arrayDeCaracteres[i];
            TrieNode filho = noCorrente.getFilho(c);
            if (filho == null) {
                filho = noCorrente.adicionarFilho(c);
            }
            noCorrente = filho;
            i++;
        }
        noCorrente.terminacao = true;
        while (noCorrente != null) {
            noCorrente.descendentes++;
            noCorrente = noCorrente.pai;
        }
    }

    public boolean temPalavra(String palavra) {
        TrieNode ultimo = encontrarUltimoNoDoPrefixo(palavra);
        return ultimo != null && ultimo.terminacao;
    }

    public boolean temPrefixo(String prefixo) {
        TrieNode ultimo = encontrarUltimoNoDoPrefixo(prefixo);
        return ultimo != null;
    }

    private TrieNode encontrarUltimoNoDoPrefixo(String prefixo) {
        TrieNode noCorrente = raiz;
        char[] arrayDeCaracteres = prefixo.toCharArray();
        int n = prefixo.length();
        int i = 0;
        while (i < n) {
            char c = arrayDeCaracteres[i];
            TrieNode filho = noCorrente.getFilho(c);
            if (filho == null) {
                return null;
            }
            noCorrente = filho;
            i++;
        }
        return noCorrente;
    }

    public int getContadorDePalavrasComPrefixo(String prefixo) {
        TrieNode ultimo = encontrarUltimoNoDoPrefixo(prefixo);
        if (ultimo == null) {
            return 0;
        }
        return ultimo.descendentes;
    }

    private class TrieNode {
        Character caracter;
        boolean terminacao;
        int descendentes;
        TrieNode pai;
        Map<Character, TrieNode> filhoByLetra;

        public TrieNode(TrieNode pai, Character caracter) {
            this.caracter = caracter;
            this.pai = pai;  // agregação
            this.filhoByLetra = new HashMap<>();  // composição
        }

        public TrieNode adicionarFilho(char letra) {
            TrieNode filho = new TrieNode(this, letra);
            filhoByLetra.put(letra, filho);
            return filho;
        }

        public TrieNode getFilho(char letra) {
            return filhoByLetra.get(letra);
        }
    }
}