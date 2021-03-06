Feature: Create Resource

  Scenario Outline: Create Json resources successful
    Given a valid resource without <side> data
    When user create <side> data resource
    Then a valid <side> json encoded should be created

    Examples:
      | side  |
      | left  |
      | right |

  Scenario Outline: Create Json resources when not exists successful
    Given a resource that not exists
    When user create <side> data resource
    Then a valid <side> json encoded should be created

    Examples:
      | side  |
      | left  |
      | right |

  Scenario Outline: Create Json resources when data is empty successful
    Given a valid resource with empty data
    When user create <side> data resource
    Then a valid <side> json encoded should be created

    Examples:
      | side  |
      | left  |
      | right |

  Scenario Outline: Create Json resources already exists fails
    Given a valid resource with differences
    When user create <side> data resource
    Then the application should return json already error

    Examples:
      | side  |
      | left  |
      | right |
