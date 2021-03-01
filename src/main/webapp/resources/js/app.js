document.addEventListener("DOMContentLoaded", function() {

    smoothScroll = function(target) {
        var scrollContainer = target;
        do { //find scroll container
            scrollContainer = scrollContainer.parentNode;
            if (!scrollContainer) return;
            scrollContainer.scrollTop += 1;
        } while (scrollContainer.scrollTop === 0);

        var targetY = 0;
        do { //find the top of target relatively to the container
            if (target === scrollContainer) break;
            targetY += target.offsetTop;
        } while (target = target.offsetParent);

   //     scroll = function(c, a, b, i) {
   //         i++; if (i > 30) return;
   //         c.scrollTop = a + (b - a) / 30 * i;
   //         setTimeout(function(){ scroll(c, a, b, i); }, 20);
   //     };
        //window.location // do sprawdzenia czy mozna uruchomic scroll
        console.log(targetY);// start scrolling
        scroll({top:targetY, behavior:"smooth"});

    };
    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }
    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function(e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;


            // TODO: get data from inputs and show them in summary

            let lastButton = document.getElementById("lastButton");

            function inputsSummary(event) {

                let lastButton = document.getElementById("lastButton");

                let categoriesAndBags = document.getElementById("categoriesAndBags");


                let categories = [];

                let chooseCategory = document.querySelectorAll("#category");
                chooseCategory.forEach(function (element) {
                    if (element.checked) {
                        const text = element.parentElement.querySelector(".description").innerText;
                        categories.push(" " + text);
                    }
                });


                let bags = document.getElementById("bags").value;
                categoriesAndBags.innerText = bags + " " + categories;

                let organisation = document.getElementById("organisation");

                let institution = null;

                const chooseInstitution = document.querySelectorAll("#institution");
                chooseInstitution.forEach(function (element) {
                    if (element.checked){
                        institution = element.parentElement.querySelector(".description").innerText;
                    }

                });

                organisation.innerText = institution;

                let street = document.getElementById("street").value;
                let city = document.getElementById("city").value;
                let zipCode = document.getElementById("zipCode").value;
                let phone = document.getElementById("phone").value;

                let streetSummary = document.getElementById("streetSummary");
                let citySummary = document.getElementById("citySummary");
                let zipCodeSummary = document.getElementById("zipCodeSummary");
                let phoneSummary = document.getElementById("phoneSummary");

                streetSummary.innerText = street;
                citySummary.innerText = city;
                zipCodeSummary.innerText = zipCode;
                phoneSummary.innerText = phone;


                let pickUpDate = document.getElementById("pickUpDate").value;
                let pickUpTime = document.getElementById("pickUpTime").value;
                let pickUpComment = document.getElementById("pickUpComment").value;

                let pickUpDateSummary = document.getElementById("pickUpDateSummary");
                let pickUpTimeSummary = document.getElementById("pickUpTimeSummary");
                let pickUpCommentSummary = document.getElementById("pickUpCommentSummary");

                pickUpDateSummary.innerText = pickUpDate;
                pickUpTimeSummary.innerText = pickUpTime;
                pickUpCommentSummary.innerText = pickUpComment;

            }


            lastButton.addEventListener("click", inputsSummary);
        }

    }
    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }


});

