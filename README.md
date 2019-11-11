# FlikSearch


### Status:
- Implemented working app
- Implemented one bonus feature (pagination)

### Build instructions:
The app was built and tested on Android Studio emulator and device. The development device was Windows. All IDE specific files are in the repo. 
Please contact me if there are any issues building!

### Features:
- **RecyclerView** to optimize usage of GPU for lists.
- Wrote template tests for **espresso tests**.
- **Avoids crashing** by logging and also by using user helpful toast messages.
- **Retrofit + RxJava** for simplifying network requests and response and also to perform network operations on background thread.
- **MVP design pattern** used to make code testable.
- **Dagger framework** used to make code modular, mockable and for simplifying dependencies.

### Potential extensions:
- A good extension would be **mockito junit** tests (for testing MovieService and for testing Presenter logic).
- Another good extension would be **Android tests** (could mock dagger component and run activity on device).
- Extracting dagger component into a **library module** could help with modularization.
- Could use **SharedPreferences** to store user specific info about movies like "favorites".
- **Picassso** could be customized to make the image fit better to the dimensions of the view.

### Existing Bug:
- During pagination, some pages are appearing again. Need to inspect how RecyclerView adapter is being updated on a response.
