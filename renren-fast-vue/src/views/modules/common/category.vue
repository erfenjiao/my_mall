<template>
    <div>
      <el-input placeholder="输入关键字进行过滤" v-model="filterText"></el-input>
      <el-tree
        :data="menus"
        :props="defaultProps"
        node-key="catId"
        ref="menuTree"
        @node-click="nodeclick"
        :filter-node-method="filterNode"
        :highlight-current = "true"
      ></el-tree>
    </div>
  </template>
  <!-- 
    node-click 被点击时回调，回调会触发 methods 中定义的方法
    node-key="catId" 区分节点
  -->


  <script>
  //这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
  //例如：import 《组件名称》 from '《组件路径》';
  
  export default {
    //import引入的组件需要注入到对象中才能使用
    components: {},
    props: {},
    data() {
      //这里存放数据
      return {
        filterText: "",
        menus: [],          // 绑定所有菜单数据
        expandedKey: [],    // 菜单默认设置
        defaultProps: {
          children: "children",  //子结点
          label: "name"
        }
      };
    },
    //计算属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {
      filterText(val) {
        this.$refs.menuTree.filter(val);
      }
    },
    //方法集合
    methods: {
      //树节点过滤
      filterNode(value, data) {
        if (!value) return true;
        return data.name.indexOf(value) !== -1;
      },

      getMenus() {
        this.$http({
          url: this.$http.adornUrl("/product/category/list/tree"),
          method: "get"
        }).then(({ data }) => {
          this.menus = data.data;  // 将菜单数据绑定给 menus， <template> <el-tree></el-tree> 自动展示
        });
      },

      // nodeclick 有三个参数，依次为：传递给data属性的数组中该结点所对应的对象、结点对应的Node、节点组件本身
      nodeclick(data, node, component) {
        console.log("子组件 category 的节点被点击", data, node, component);
        //向父组件发送事件；让 attrgroup 知道
        this.$emit("tree-node-click", data, node, component);
      }
    },


    //生命周期 - 创建完成（可以访问当前this实例）
    created() {
      this.getMenus();
    },

    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {},
    beforeCreate() {}, //生命周期 - 创建之前
    beforeMount() {}, //生命周期 - 挂载之前
    beforeUpdate() {}, //生命周期 - 更新之前
    updated() {}, //生命周期 - 更新之后
    beforeDestroy() {}, //生命周期 - 销毁之前
    destroyed() {}, //生命周期 - 销毁完成
    activated() {} //如果页面有keep-alive缓存功能，这个函数会触发
  };
  </script>
  <style scoped>
  
  </style>