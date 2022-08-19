## Construire le plugin

Prérequis:
- [Git](https://git-scm.com)
- JDK 17

Vous **devez** utiliser le wrapper présent dans la repo.

Linux: `./gradlew` ou `sh gradlew` | Windows vous devez utilisez `gradle.bat`

1. `git clone https://github.com/Elikill58/Negativity.git`
2. Créer le dossier `spigotJars` dans `spigot/`
3. Télécharger tous les spigot depuis [ce lien](https://getbukkit.org/download/spigot) et mettez-les dans `spigot/spigotJars` Versions:
   `1.7.10, 1.8.8, 1.9.2, 1.9.4, 1.10.2, 1.11.2, 1.12.2, 1.13`(vous devez ajouter un + devant son nom (+spigot1.13.jar par exemple)`,
   1.13.2, 1.14.4, 1.15.2, 1.16.1, 1.16.5`
4. If faut utiliser le buildtools pour Spigot 1.17/1.18/1.18.2/1.19 (ou les mettre en commentaire `settings.gradle`). Il faut placer le jar remapé dans 
   `spigot/spigot` + 17 pour spigot 1.17, + 18 pour spigot 1.18, + 18.2 pour spigot 1.18.2, + 19 pour spigot 1.19. par exemple `spigot/spigot182`  
5. Construire le plugin avec `gradlew build`
- Toutes les plateformes `/build/libs/`
- Plateforme spécifique (`/spigot/build/libs/` pour exemple)