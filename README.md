# Resoudre des analogies avec des word embeddings
<p>A l'aide de word embeddings,qui sont des representations de mots sous la forme de vecteurs de nombres reels, ce progamme se servit à chercher les k mots les plus similaires par rapport à un mot..<p/>
<p>Les mots saisis par l'utilisateur ne peuvent pas contenir les mots clés reservé par notre programme, car les mots clés sont utilisés pour l'expression arithmétique. Les mots clés sont "-","+","(",")".Notre progamme est capable de chercher les k mots les plus similaires avec le mot saisi par l'utilisateur qui déjà existe dans le corpus. Sinon, notre programme va lancer un WordNotFoundException. <p/>
<p> Les utilisateurs peuvent aussi saisir une expression arméthique. Notre programme est capable d'evaluer l'expression arithmétique.Dans notre programme, on utilise la notation infixe pour définir la expression arithmétique. le grammaire de language de requête est difini par ci-dessous:<p/>

```
 expressions:
 expr     :=      word
           |     expr "+" expr 
           |     expr "-" expr
           |     '(' expr ')'
           |       - expr
  word   :=  (~["+","-","(",")"," ","\n","\t"])?
```
## 1.structure de fichier
Cette dossier contiens les codes de sources, le javadoc et le fichier executable binaire.
  * src/    tous les fichiers de code de source.Notre programme contiens deux pakage. Le classe principale est Main.java que se situe dans le pakage calculatirce. 
  * bin/    ce dossier contient tous les fichiers d'éxetuable.
  * log/    ce dossier contient tous les log
  * doc/    ce dossier contient tous les javadoc

## 2.mode d'emploi
Notre programme principal est dans une classe Main. Comme cette dossier contient le fichier d'éxetutable,vous pouvez utiliser le command ci-dessous lancer notre programme directement:
```
bin> java -cp . calculatrice.Main <path of corpus> -k 10
```
Vous pouvez aussi utiliser le command si-dessous:
```
java -jar WordEmbedings.jar <path of corpus> -k 10
```
Le programme a un argument obligatoir(le chemin vers ke corpus) et u argument facultatif(le nombre de résultats pour chaque requête)
Vous pourvez aussi compiler notre programme par vous-même avec le command ci-dessous. Comme on utilise les nouveaux fonctionalité de java 8, je vous conseille d'utiliser java 8 pour compliler notre programme.



