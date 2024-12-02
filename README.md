# github-activity-cli
The GitHub Activity CLI is a command-line application that allows users to fetch and display the recent activity of any GitHub user by providing their username. 
It interacts with the GitHub API to retrieve and display this data directly in the terminal.

# How it works
* The CLI accepts a GitHub username as input.

* It constructs a request to the GitHub API endpoint:

  https://api.github.com/users/<username>/events

* It fetches the user’s recent activity and parses the response.

* Activity details such as type of event and repository name are displayed in a readable format.

# Features

Fetch the recent activity of a GitHub user using their username. Display events such as:

* Pushed commits

* Opened or closed issues

* Starred repositories

* Other common GitHub actions

* Gracefully handles errors, such as invalid usernames or API failures.

# Usage

Clone the repository:

    git clone https://github.com/yourusername/github-activity-cli.git

Navigate to the project directory:

    cd github-activity-cli

Compile the project (if applicable):

    javac GitHubActivityCLI.java

Run the application with a GitHub username as an argument:

    java GitHubActivityCLI <username>

Example:

java GitHubActivityCLI kamranahmedse

The CLI will fetch and display the user’s recent activity in the following format:

- Pushed 3 commits to kamranahmedse/developer-roadmap
- Opened a new issue in kamranahmedse/developer-roadmap
- Starred kamranahmedse/developer-roadmap

# Contributing

Feel free to fork the repository and submit a pull request for any enhancements or bug fixes.

