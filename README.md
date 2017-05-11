# SearchGoogleImages
Parse images from google custom search and show in a grid view

## GoogleSearchImageParser dependency
```gradle

repositories {
    maven { url 'https://github.com/anthorlop/mvn-android/raw/master/' }
}

// GoogleSearchImageParser gradle dependencies
compile 'es.lombrinus.projects.mods:GoogleSearchImageParser:1.0'
```

## Getting image items
```java

        GImageParser.get(texto, API_KEY, SEARCH_CX, size, start, new GImageParserListener() {
            @Override
            public void onError(String error) {

                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(List<Item> itemList) {

                mAdapter = new MainAdapter(MainActivity.this, itemList, MainActivity.this);

                mGrid.setAdapter(mAdapter);

            }
        });

```

You can generate your own Custom Search:
API KEY: https://console.developers.google.com/apis/credentials
SEARCH ID: https://cse.google.es/cse

![alt text](captura.jpeg)
