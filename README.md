<!-- (This is a comment) INSTRUCTIONS: Go through this page and fill out any **bolded** entries with their correct values.-->

# AND101 Project 6 - CYOAPI Part 2: RecyclerView Edition

Submitted by: **Reneca Capuno**

Time spent: **6** hours spent in total

## Summary

**Poke Present** is an android app that **displays a scrollable list of Pokemon with their respective name, height, and weight!**

If I had to describe this project in three (3) emojis, they would be: ** ❗ ⬆️ 🧠 **

## Application Features

<!-- (This is a comment) Please be sure to change the [ ] to [x] for any features you completed.  If a feature is not checked [x], you might miss the points for that item! -->

The following REQUIRED features are completed:

- [x] Make an API call to an API of your choice using AsyncHTTPClient
- [x] Implement a RecyclerView to display a list of entries from the API
- [x] Display at least three (3) pieces of data for each RecyclerView item

The following STRETCH features are implemented:

- [ ] Add a UI element for the user to interact with API further
- [ ] Show a `Toast` or `Snackbar` when an item is clicked
- [ ] Add item dividers with `DividerItemDecoration`

The following EXTRA features are implemented:

- [ ] List anything else that you added to improve the app!

- Updated MainActivity.kt: Implemented logic in MainActivity.kt to fetch Pokémon data from the PokeAPI and populate the RecyclerView with Pokémon entries.
- Defined Pokemon Data Class: Created a data class named Pokemon to represent Pokémon objects, containing properties such as name, id, types, imageUrl, height, and weight.

## Video Demo

Here's a video / GIF that demos all of the app's implemented features:

<img src='https://i.imgur.com/jCL1MYu.gif' title='Video Demo' width='' alt='Video Demo' />

GIF created with **LICEcap**

<!-- Recommended tools:
- [Kap](https://getkap.co/) for macOS
- [ScreenToGif](https://www.screentogif.com/) for Windows
- [peek](https://github.com/phw/peek) for Linux. -->

## Notes

Here's a place for any other notes on the app, it's creation process, or what you learned this unit!

## License

Copyright **2024** **Reneca Capuno**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
