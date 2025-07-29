<!-- TOC start (generated with https://github.com/derlin/bitdowntoc) -->

- [Snap](#snap)
    * [UX ( 5 planes of UX )](#ux-5-planes-of-ux-)
        + [Strategy](#strategy)
        + [Scope](#scope)
        + [Surface](#surface)
        + [Structure](#structure)
        + [Skeleton](#skeleton)
    * [Testing](#testing)
    * [Deployment ](#deployment)
        + [Github guide](#github-guide)
    * [Credits](#credits)
        + [Technologies used](#technologies-used)
        + [Code and resources used](#code-and-resources-used)
        + [Acknowledgements](#acknowledgements)

<!-- TOC end -->

<!-- TOC --><a name="snap"></a>
# Snap

<!-- TOC --><a name="ux-5-planes-of-ux-"></a>
## UX ( 5 planes of UX )

<!-- TOC --><a name="strategy"></a>
### Strategy

**Project overview**

Snap is a card game, playable from a terminal. This is a recreation of the card game with a few small twists. The game can be played cooperatively, supporting 1 up to 8 players.

The game is relatively simplistic especially considering this game is played only from a terminal. The build is purely java made and includes a CardGame base class which can be extended for any extra card games in future iterations.


<!-- TOC --><a name="scope"></a>
### Scope


Going into the project there is a general outline of the requirements, rules and tasks that need to be done to make the game.
I broke some tasks down further wherever necessary and placed it in the **Plan-notes** file which can be found at the **root level** of the folder.
The game was made in stages 1-4 each stage answering different need.

To ensure I was able to deliver the project in scope I went from stage 1-4 progressively and focused on delivering an MVP.
After I was able to achieve my MVP I added further features such as increasing the player limit from 2 up to 8, player name customisation and game continuation after each round.

<!-- TOC --><a name="surface"></a>
### Surface

As this is a game played in the terminal there is no documentation for surface.

<!-- TOC --><a name="structure"></a>
### Structure

The program being written in java is segregated in a modular fashion. Classes and files are segregated into the package subfolders of model, service and util(Currently not populated).
A class of CardGame has been made which can be extended to be used in different card games and is currently extending our Snap game.

<!-- TOC --><a name="skeleton"></a>
### Skeleton

As this is a game played in the terminal there is no need for wireframes.

<!-- TOC --><a name="testing"></a>
## Testing

- As of this iteration of the project no testing has been implemented

<!-- TOC --><a name="deployment"></a>
## Deployment

<!-- TOC --><a name="github-guide"></a>
### Github guide

As this GitHub repository is public anyone who would like to work on this repo can do so by following the below steps

**Cloning**
To clone a repository follow these steps

1. Login in to GitHub or create an account if you haven't already

2. Go to this project repository [Snap card game](https://github.com/JamesBracken/snap-card-game);

3. Click on the *Code* button and select whether you would like to cline with HTTPS, SSH or the GitHub CLI and then copy the link shown

4. Open your terminal in your IDE/code editor of choice then change the current working directory to the location you would like to use for the cloned directory

5. Type "git clone" into the terminal and paste the link you copied in the third step then press Enter

6. To install the dependencies you can run **npm install** in your IDE(integrated development environment) terminal, the dependencies you need are listed in the package.json and package-lock.json files

**Commiting and Pushing Changes**

1. Open the terminal in the directory of your cloned repository

2. Using "git status" check to see your changes are correct

3. If you are ready to commit  type "git add ." to stage all saved changes to be commited, you can alternatively use "git add YOUR_FILENAME" to stage specific files for commit

4. Using git commit -m "Write your commit message here" commit your changes with a descriptive message, give a good amount of detail but try not to go over 50 characters

5. Finally use "git push origin main" to push your changes to the main branch of your GitHub repository


**Forking**

To fork this repository follow these steps

1. Setup Git and make sure your git has GitHub authentication

2. Go to the web page repository [Snap card game](https://github.com/JamesBracken/snap-card-game)

3. Click on *fork* on the upper right portion of the page.

4. This has now forked the repository to your own profile.

5. Go to your profile and navigate to the forked repository.

6. Click on *Code* above the list of forked files

7. Choose the option you need from the dropdown menu.

Further assistance can be found [HERE](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/working-with-forks/fork-a-repo) on the GitHub Fork a Repo page

**Local development**

1. Clone your repository from GitHub, you can find the "Code" button where you can copy the url from

2. Open your IDE and open a terminal, make sure you are in the correct directory where you want to clone the repository to.

3. Type git clone URL, replace URL with the one you just copied in the first step


<!-- TOC --><a name="credits"></a>
## Credits

<!-- TOC --><a name="technologies-used"></a>
### Technologies used

1. Java - The programming language used to develop this project. Java is a high-level, OOP language.
2. IntelliJ Idea - IDE used for development, aided with detailed code explanations, autofill and other tools
3. Chatgpt - Used to aid in development
4. Google, Stack overflow, w3Schools etc. - Used to aid development with code examples and explanations
5. GitHub - Provides version control, can host code repositories and website deployment

<!-- TOC --><a name="code-and-resources-used"></a>
### Code and resources used

No code was copied directly copied in the development of this project. Resources were used and examples served as an aid for development, these have been listed in technologies.

<!-- TOC --><a name="acknowledgements"></a>
### Acknowledgements

This is my first java project! I made this while doing training at nology, a big thank you to my teacher Remi Hoeppe who provided training, guidance and support for this project.