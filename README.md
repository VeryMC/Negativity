# Negativity

Negativity est un AC développé par [Elikill58](https://github.com/Elikill58), ce plugin est disponible sur spigot.
- [V1 (lien vers spigot)](https://www.spigotmc.org/resources/ac-negativity-spigot-1-7-1-19-sponge-bungeecord-velocity.48399)
- [V2 (lien vers spigot)](https://www.spigotmc.org/resources/negativity-v2-1-7-to-1-19-bungee-velocity-sponge-fabric.86874)

Pour le contacter, voici son discord (`Elikill58#0743`) ou son [serveur discord](https://discord.gg/KHRVTX2).

Suggestions et reports [issue tracker](https://github.com/Elikill58/Negativity/issues).

**VeryMc n'est pas le développeur du plugin**

# Pourquoi avoir fork ?

Pour nous permettre par exemple d'intégrer le plugin dans notre jenkins ou encore de faire une version lite (légère) pour nos serveurs.
Pourquoi une version lite ? On n'a pas besoin de plugins inclus dans le jar ni de supporter du moddé.

# Pourquoi cette branche est différente des autres ?

Cette branche est actuellement utilisée sur VeryMc.

Elle contient la version spigot(1.7.10 à 1.16.5 + 1.18.2) + velocity

## Construire le plugin

Prérequis:
- [Git](https://git-scm.com)
- JDK 17

Vous **devez** utiliser le wrapper présent dans la repo.

Linux: `./gradlew` ou `sh gradlew` | Windows vous devez utilisez `gradle.bat`

1. `git clone https://github.com/VeryMC/Negativity.git`
2. Changer de branche si nécéssaire, les conditions ici ne fonctionneront peut être pas dans une autre branche
3. Créer le dossier `spigotJars` dans `spigot/`
4. Télécharger tous les spigot depuis [ce lien](https://getbukkit.org/download/spigot) et mettez-les dans `spigot/spigotJars` Versions:
   `1.7.10, 1.8.8, 1.9, 1.9.4, 1.10.2, 1.11.2, 1.12.2, 1.13`(vous devez ajouter un + devant son nom (+spigot1.13.jar par exemple)`,
   1.13.2, 1.14.4, 1.15.2, 1.16.1, 1.16.5`
5. If faut utiliser le buildtools pour Spigot 1.17/1.18/1.18.2/1.19 (ou les mettre en commentaire `settings.gradle`). Il faut placer le jar remapé dans
   `spigot/spigot` + 17 pour spigot 1.17, + 18 pour spigot 1.18, + 18.2 pour spigot 1.18.2, + 19 pour spigot 1.19. par exemple `spigot/spigot182`
6. Construire le plugin avec `gradlew build`
- Toutes les plateformes `/build/libs/`
- Plateforme spécifique (`/spigot/build/libs/` pour exemple)
