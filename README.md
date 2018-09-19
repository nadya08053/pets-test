##About adoption eligibility:

Depending on the use-cases and required versatility, we can use one of the multiple approaches. The simplest of them is to add additional fields into the Pet class, which will contain information about pet's presence (adopted or present in the shelter), fees and etc.
We also can make a separate class with adoption info. It will contain Id, adoption date, some information about adopter and fees. It multiple pets can be adopted in a single record, we can then put adoption id into a separate field inside of Pet class. Otherwise, we can add pet id field into Adoption class. Then we can check if pet already has some adoption records or it can be adopted by someone.
If adoption can be somehow reverted, we'll need a flag for marking it in adoption class.
Fees can be moved to a separate class too. It might be useful if we'll need multiple fees or adopter will pay them in multiple payments.
The adoption process might require another way of sending params to the program since we'll need to show adoption eligibility before adopting and ask client about it.

##About testing:

Some of the exceptional situation of the application are already checked, but there still is a place for improvement. For now, we might need to test mostly the data loading process with wrong quantity of params (or badly formatted params) and input file format. We might also need to test this program with large sets of input data.
Integration testing of current version of the program will depend on the rest of the modules and environment. For now, this program itself is simple and it doesn't rely on some other modules or programs so integration tests of it won't give a large effect.

##About version control systems:

They are really useful since they give us ability to track our changes and avoid extra problems related to the in-team version coordination. But it can be a problem if a team doesn't have a stable and reliable approach to working with code, branches and merging. If everyone is working on their own, it can cause a lot of merge conflicts and inconveniences. 
